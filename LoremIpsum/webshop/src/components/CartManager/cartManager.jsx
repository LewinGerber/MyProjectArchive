import { React, useState, useEffect } from 'react';

import './cartManagerStyle.css';

import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import ExpandLessIcon from '@material-ui/icons/ExpandLess';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import { Button, Fab, Dialog } from '@material-ui/core';

export const Cart = (props) => {
    //the total price of all the products times their stock
    const [totalPrice, setTotalPrice] = useState(0);
    //all the items in the shopping cart (based on the array managed in 'Application.js')
    const [cartProducts, setCartProducts] = useState([...props.cartProducts]);

    const [open, setOpen] = useState(false);
    /*
        ////////////////////////////////
        toggle open popover
    */
    function toggleOpen() {
        setOpen(!open);
    }

    /*
        ////////////////////////////////
        remove cart product and clear total price
    */
    function removeCartProduct(id) {
        props.removeCartProduct(id);
        setTotalPrice(0);
    }

    /*
        ////////////////////////////////
        calculate total price
    */
    function calculateTotalPrice(cartProducts) {
        //create temporary totalPrice
        let totalPrice = 0;
        //increase the total price by them price * quantity for every product
        cartProducts.map(cartProduct => {
            totalPrice += cartProduct.price * cartProduct.quantity;
        });
        return totalPrice;
    }

    /*
        ////////////////////////////////////////////////////////////////
        update the total price whenever the cartProducts change
    */
    useEffect(() => {
        //update cart products
        setCartProducts(props.cartProducts);

        //calculate and set new total price
        let tempTotalPrice = calculateTotalPrice(props.cartProducts);
        setTotalPrice(tempTotalPrice);

    }, [props.cartProducts]);

    function clickIncrease(e, id) {
        e.preventDefault();
        props.increaseQuantity(id);
    }

    function clickDecrease(e, id) {
        e.preventDefault();
        props.decreaseQuantity(id);
    }

    return (
        <div id="cart">
            {
                cartProducts.map(cartProduct => {
                    return (
                        <div key={cartProduct.id} id="cart-item">
                            <p id="product-title">{cartProduct.name}</p>
                            <div id="right-container">
                                <p id="quantity-indicator"> {cartProduct.quantity}</p>
                                <div id="increase-decrease-container">
                                    <div id="increase-button" className="button-xd" onClick={(e) => clickIncrease(e, cartProduct.id)} ><ExpandLessIcon /></div>
                                    <div id="decrease-button" className="button-xd" onClick={(e) => clickDecrease(e, cartProduct.id)} ><ExpandMoreIcon /></div>
                                </div>
                                <Fab id="delete-btn" onClick={() => removeCartProduct(cartProduct.id)} ><DeleteForeverIcon /></Fab>
                            </div>
                        </div>
                    )
                })
            }
            <div id="resolution">
                <h2>TOT. {totalPrice}</h2>
                <Button id="btn-check" variant="contained" color="default" onClick={() => toggleOpen()}>CHECKOUT</Button>
            </div>

            <Dialog
                open={open}
                onClose={toggleOpen}
                style={{ width: '100rem !important' }}
            >
                <div id="checkout">
                    <h1 style={{fontWeight: 'bold', margin: 0, padding: 0}}>Lorem Ipsum</h1>
                    <p style={{margin: 0, paddingTop: '0.5rem', paddingBottom: '1rem'}}>These are your products:</p>
                    <div id="product-summary">
                        {
                            cartProducts.map(cartProduct => {
                                return (
                                    <div id="pp-sm">
                                        {cartProduct.description}
                                    </div>
                                );
                            })
                        }
                    </div>
                </div>
            </Dialog>
        </div>
    );
}

export default Cart;