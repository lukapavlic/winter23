import { Button, CircularProgress } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { MeasurementsApi } from '../../api/api';
import AddProduct from './AddProduct';
import EditProduct from './EditProduct';
import ProductsTable from './ProductsTable';

const Products = () => {
    const [products, setProducts] = useState([]);
    const [open, setOpen] = useState(false);
    const [editOpen, setEditOpen] = useState(false);
    const [editId, setEditId] = useState("");
    const [loaded, setLoaded] = useState(false);
    const measurementsApi = new MeasurementsApi();

    const fetchData = () => {
        measurementsApi.getProducts()
            .then((result) => { setProducts(result.data); setLoaded(true); })
            .catch((response) => console.log(`error ${response}`));
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
        await measurementsApi.deleteProduct(id);
        fetchData();
    }
    const editProduct = (id) => {
        setEditId(id);
        setEditOpen(true);
    }
    return (
        <div style={{ padding: '25px' }}>
            <h2>Products</h2>
            <Button variant="contained" onClick={handleOpen}>New Product</Button>
            <br />
            <br />
            {loaded ? <ProductsTable products={products} deleteProduct={deleteProduct} editProduct={editProduct} /> : <CircularProgress />}
            <AddProduct open={open} handleClose={handleClose} />
            <EditProduct open={editOpen} handleClose={handleClose} id={editId} />
        </div>
    );
}

export default Products;