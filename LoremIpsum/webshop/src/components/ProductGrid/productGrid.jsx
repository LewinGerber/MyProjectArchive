import { React, useState } from 'react';
import './productGridStyle.css';

import { Dialog, Button } from '@material-ui/core';

const Product = (props) => {
    const [open, setOpen] = useState(false);
    /*
        ////////////////////////////////
        toggle open popover
    */
    function toggleOpen() {
        setOpen(!open);
    }

    return (
        <div id="product-item">
            <Dialog
                open={open}
                onClose={toggleOpen}
                style={{ width: '100rem !important' }}
            >
                <div id="popup">
                    <div id="image-showcase">
                        <img  className="showcase-image" src={"data:image/png;base64," + props.image} alt={"product image: " + props.name}/>
                    </div>
                    <div id="popup-content">
                        <h1>{props.name}</h1>
                        <p>{props.description}</p>
                        <Button id="pop-button" onClick={() => { props.addCartProduct(props.id); toggleOpen();}} variant="contained" color="default">ADD FOR {props.price} CHF</Button>
                    </div>
                </div>
            </Dialog>

            <div onClick={() => toggleOpen()} id="product-hitbox">
                <img  className="product-image" src={"data:image/png;base64," + props.image} alt={"product image: " + props.name}/>
                    <div id="product-label">
                        <p>{props.name}</p>
                        <p>{props.price + 'CHF'}</p>
                    </div>
            </div>
        </div>
    );
}

export const ProductGrid = (props) => {

    return (
        <div id="product">
            {
                //map over all products and create a new product component 
                props.products.map(product => {
                    //check if the product is still available
                    if (product.stock > 0) {
                        return (
                            <div key={product.id} id="product-itself">
                                <Product
                                    id={product.id}
                                    name={product.name}
                                    description={product.description}
                                    price={product.price}
                                    image={product.image}
                                    addCartProduct={(id) => props.addCartProduct(id)}
                                />
                            </div>
                        );
                    }
                    return null;

                })
            }
        </div>
    );
}

export default ProductGrid;