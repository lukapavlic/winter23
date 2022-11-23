import { Button } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { measurementsApi } from '../../api/api';
import AddProduct from './AddProduct';
import ProductsTable from './ProductsTable';

const Products = () => {
    const [products, setProducts] = useState([]);
    const [open, setOpen] = useState(false);
    useEffect(() => {
        const fetchData = async () =>{
            const result = await measurementsApi.get("/products");
            setProducts(result.data);
            console.log("fetching")
        }
        fetchData();

    }, []);
    const handleOpen = () => {
        setOpen(true);
    }
    const handleClose = () => {
        setOpen(false);
    }
    const deleteProduct = async (id) => {
        const result = await measurementsApi.delete(`/products/${id}`);
        console.log(result);
    }
    return (
        <div style={{padding: '25px'}}>
            <h2>Products</h2>
            <Button variant="contained" onClick={handleOpen}>New Product</Button>
            <br />
            <br />
            <ProductsTable products={products} deleteProduct={deleteProduct}/>
            <AddProduct open={open} handleClose={handleClose} />
        </div>
    );
}

export default Products;