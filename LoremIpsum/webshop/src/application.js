import { React, useState, useEffect } from 'react';
import axios from 'axios';

import ProductGrid from './components/ProductGrid/productGrid.jsx';
import Cart from './components/CartManager/cartManager.jsx';
import Login from './components/LoginManager/loginManager.jsx';

import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import PersonIcon from '@material-ui/icons/Person';

import './style.css';

export const Application = () => {
    //list of all the available products (retrieved from db)
    const [products, setProducts] = useState([]);
    //list of all the items in the cart (if logged in also compared to db)
    const [cartProducts, setCartProducts] = useState([]);
    //cart code (global for db)
    const [cartCode, setCartCode] = useState("-");
    //mode
    const [mode, setMode] = useState(false);
    const [modeTwo, setModeTwo] = useState(false);
    const [modeThree, setModeThree] = useState(false);

    /* 
        ////////////////////////////////
        handle change of cart code
    */
    function handleCartCodeChange(code) {
        //locally
        setCartCode(code);
        //db and localstorage
        updateCartCode(code);
    }

    /* 
        ////////////////////////////////
        handle change of cart products
    */
    function handleCartProductsChange(cartProducts) {
        setCartProducts(cartProducts);
        handleCartCodeChange(encodeCart(cartProducts));
    }

    /* 
        ////////////////////////////////
        handle change of cart products
    */
    async function loadCartCode() {
        let newCartCode = "";

        if (localStorage.getItem("lit_cart") !== undefined){
            newCartCode = localStorage.getItem("lit_cart");
        }

        if (localStorage.getItem("lit") !== undefined) {
            axios.defaults.headers.common[
                "Authorization"
            ] = localStorage.getItem("lit");
            try {
                const cartCode = await axios.get('/api/getUserCart')
                if (cartCode.data !== null && cartCode.data !== undefined && cartCode.data !== "") {
                    newCartCode = cartCode.data;
                    localStorage.setItem("lit_cart", cartCode.data);
                }
            } catch (err) { }
        } 

        if(newCartCode !== "" && newCartCode !== undefined && newCartCode !== null) {
            decodeCartCode(newCartCode);
        }
            
    }

    /*
        ////////////////////////////////////////////////////////////////
        get all the products from the db
    */
    async function getProducts() {
        try {
            //call to backend
            const result = await axios.get('/api/getProducts');
            //set the state variable to the information from the db
            setProducts(result.data);
        } catch (e) {
            console.error('Products not retrieved: ' + e.message);
        }
    }

    /*
        ////////////////////////////////////////////////////////////////
        add a product to the array by id (with necessary checks)
    */
    async function addCartProduct(id) {
        let duplicate = false;
        //check if it is duplicate
        cartProducts.forEach(cartProduct => {
            // if the products is already in list -> mark as duplicate
            if (cartProduct !== undefined) {
                if (cartProduct.id === id)
                    duplicate = true;
            }
        });
        //scenarios for duplicate (either add or increase quantity)
        if (duplicate) {
            increaseCartProductQuantity(id);
        } else {
            products.forEach(product => {
                // if the products match 
                if (product.id === id) {
                    let cartProduct = product;
                    cartProduct.quantity = 1;
                    //add item to cartItems
                    handleCartProductsChange([cartProduct, ...cartProducts]);
                }
            });
        }
    }

    async function initiateProduct(newProduct) {
        try {
            //call to backend
            const result = await axios.get('/api/getProducts');
            let duplicate = false;
            //check if it is duplicate
            cartProducts.forEach(cartProduct => {
                // if the products is already in list -> mark as duplicate
                if (cartProduct !== undefined) {
                    if (cartProduct.id === newProduct.id)
                        duplicate = true;
                }
            });
            //scenarios for duplicate (either add or increase quantity)
            if (duplicate) {
                increaseCartProductQuantity(newProduct.id);
            } else {
                result.data.forEach(product => {
                    // if the products match 
                    if (product.id === newProduct.id) {

                        let cartProduct = product;
                        if (newProduct.quantity > 1) {
                            cartProduct.quantity = newProduct.quantity;
                        } else {
                            cartProduct.quantity = 1;
                        }
                        //add item to cartItems
                        handleCartProductsChange([cartProduct, ...cartProducts]);
                    }
                });
            }
        } catch (e) { }
    }

    /*
        ////////////////////////////////////////////////////////////////
        increase quantity of certain product in cartProducts
    */
    function increaseCartProductQuantity(id) {
        for (var i = 0; i < cartProducts.length; i++) {
            if (id === cartProducts[i].id) {
                //take the the matching product
                let product = cartProducts[i];
                //increase the quantity by 1
                product.quantity = parseInt(product.quantity) + 1;
                //copy the array
                let tempCartProducts = cartProducts;
                //remove the old item and set in the new one
                tempCartProducts.splice(i, 1, product);
                //save the array to the state
                handleCartProductsChange([...tempCartProducts]);
            }
        }
    }

    /*
        ////////////////////////////////////////////////////////////////
        decrease quantity of certain product in cartProducts
    */
    function decreaseCartProductQuantity(id) {
        //find the matching product in cart
        for (var i = 0; i < cartProducts.length; i++) {
            //if the id's match
            if (id === cartProducts[i].id) {
                //take the the matching product
                let product = cartProducts[i];
                //decrease the quantity by 1
                if (product.quantity >= 2)
                    product.quantity -= 1;

                //copy the array
                let tempCartProducts = cartProducts;
                //remove the old item and set in the new one
                tempCartProducts.splice(i, 1, product);
                //save the array to the state
                handleCartProductsChange([...tempCartProducts]);
            }
        }
    }

    /*
        ////////////////////////////////////////////////////////////////
        remove a product from the cart
    */
    function removeCartProduct(id) {
        //make a copy of the local
        let allCartProducts = cartProducts;
        //find the index of the product
        for (var i = 0; i < allCartProducts.length; i++) {
            //if the id's match
            if (id === allCartProducts[i].id)
                allCartProducts.splice(i, 1);
        }
        //set the changed array equal to useState
        handleCartProductsChange(allCartProducts);
    }

    /*
        ////////////////////////////////////////////////////////////////
        cart code encoder - saves the code to localstorage and returns it
    */
    function encodeCart(cartProducts) {
        let cartCode = "-";
        cartProducts.map(cartProduct => {
            //add the encoded version of every product
            if (cartProduct !== undefined)
                cartCode += cartProduct.id + 'A' + cartProduct.quantity + '-';
        });
        return cartCode;
    }

    /*
        ////////////////////////////////////////////////////////////////
        cart code decoder - and saves the data in array
    */
    async function decodeCartCode(code) {
        let elements = code.split('-');
        try {
            //call to backend
            const result = await axios.get('/api/getProducts');
            elements.forEach(element => {
                if (element !== "" && element !== undefined) {
                    //alway only 2 elements (0 and 1)
                    let productParts = element.split('A');
                   
                    result.data.map(product => {
                        console.log("im here! with: " + productParts[0] + " -> " + product.id);
                        console.log(product.id === parseInt(productParts[0]));
                        if (product.id === parseInt(productParts[0])) {
                            
                            let tempProduct = product;
                            tempProduct.quantity = productParts[1];
                            
                            initiateProduct(tempProduct);
                        }
                    });
                }
            });
        } catch (e) { }
    }

    /*
        ////////////////////////////////////////////////////////////////
        turn on crazy mode
    */
    function toggleMode() {
        setMode(!mode);
    }
    function toggleModeTwo() {
        setModeTwo(!modeTwo);
    }
    function toggleModeThree() {
        setModeThree(!modeThree);
    }

    /* 
        ////////////////////////////////
        update cart code - DB and localstorage
    */
    async function updateCartCode(code) {
        try {
            // check if user is authenticated
            const result = await axios.get('/api/isAuthenticated');
            if (result.data === true) {
                //set header
                axios.defaults.headers.common["Authorization"] = localStorage.getItem("lit");

                //send cart code to db
                const cartCode = await axios.post('/api/updateUserCart', code);
            }
            //save cart code to local storage
            localStorage.setItem("lit_cart", code);
        } catch (e) { console.log(e) }
    }

    /*
         ////////////////////////////////////////////////////////////////
         on load
    */
    useEffect(() => {
        //on load get all the items from the db
        getProducts();
    });

    return (
        <div>
            <div id="bg" className={mode?'color-switch-animation':''} />
            <div id="mode-trigger" onClick={() => toggleMode()} />
            <div id="mode-trigger-2" onClick={() => toggleModeTwo()} />
            <div id="mode-trigger-3" onClick={() => toggleModeThree()} />
            <div id="spliter">
                <div className={modeThree?'show':''} id="title-presentation"><h1>🎆Vielen Dank fürs zuhören! 🎇</h1></div>
                <div id="views-flexer" className={modeTwo?'rotate-switch-animation':''}>
                    <div id="product-view">
                        <ProductGrid
                            products={products}
                            addCartProduct={(id) => addCartProduct(id)}
                        />
                    </div>
                    <div id="menu-view">
                        <div id="title-area">
                            <h1>LOREM IPSUM</h1>
                            <p>
                                Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,
                                sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren,
                                no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,
                                sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
                        </p>
                        </div>
                        <div id="cart-login-container">
                            <div id="cart-container">
                                <h1><ShoppingCartIcon fontSize="large" id="icon"/>Cart</h1>
                                <Cart
                                    cartProducts={cartProducts}
                                    increaseQuantity={(id) => increaseCartProductQuantity(id)}
                                    decreaseQuantity={(id) => decreaseCartProductQuantity(id)}
                                    removeCartProduct={(id) => removeCartProduct(id)}
                                />
                            </div>
                            <div id="login-container">
                                <h1><PersonIcon fontSize="large" id="icon"/>Login</h1>
                                <Login
                                    loadCartCode={() => loadCartCode()}
                                    cartCode={cartCode}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Application;