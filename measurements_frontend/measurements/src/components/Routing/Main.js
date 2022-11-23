import React from 'react';
import { Routes, Route } from "react-router-dom";
import Measurements from '../Measurements/Measurements';
import PageNotFound from "../PageNotFound/PageNotFound";
import Products from '../Products/Products';

const Main = () => {
    return (
        <Routes>
            <Route path="/" element={<Measurements />} />
            <Route path="/products" element={<Products />} />
            <Route path="*" element={<PageNotFound />} />
        </Routes>
    );
}

export default Main;