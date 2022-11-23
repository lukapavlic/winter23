import { Button } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { measurementsApi } from '../../api/api';
import AddProduct from './AddProduct';
import EditProduct from './EditProduct';
import ProductsTable from './ProductsTable';

const Products = () => {
    const [products, setProducts] = useState([]);
    const [open, setOpen] = useState(false);
    const [editOpen, setEditOpen] = useState(false);
    const [editId, setEditId] = useState("");

    const fetchData = async () =>{
        const result = await measurementsApi.get("/products");
        setProducts(result.data);
    }
    useEffect(() => {
        fetchData();

    }, []);
    const handleOpen = () => {
        setOpen(true);
    }
    const handleClose = () => {
        setOpen(false);
        setEditOpen(false);
        setEditId("");
        fetchData();
    }
    const deleteProduct = async (id) => {
        const result = await measurementsApi.delete(`/products/${id}`);
        fetchData();
    }
    const editProduct = (id) => {
        setEditId(id);
        setEditOpen(true);
    }
    return (
        <div style={{padding: '25px'}}>
            <h2>Products</h2>
            <Button variant="contained" onClick={handleOpen}>New Product</Button>
            <br />
            <br />
            <ProductsTable products={products} deleteProduct={deleteProduct} editProduct={editProduct}/>
            <AddProduct open={open} handleClose={handleClose} />
            <EditProduct open={editOpen} handleClose={handleClose} id={editId} />
        </div>
    );
}

export default Products;