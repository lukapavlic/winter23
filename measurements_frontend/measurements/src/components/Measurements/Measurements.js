import { Button } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { measurementsApi } from '../../api/api';
import AddMeasurement from './AddMeasurement';
import MeasurementsTable from './MeasurementsTable';

const Measurements = () => {
    const [measurements, setMeasurements] = useState([]);
    const [open, setOpen] = useState(false);
    const fetchData = async () =>{
        const result = await measurementsApi.get("/history");
        setMeasurements(result.data);
        console.log(result.data);
    }
    useEffect(() => {
        fetchData();
    }, []);
    const handleOpen = () => {
        setOpen(true);
    }
    const handleClose = () => {
        setOpen(false);
        fetchData();
    }
    return (
        <div style={{padding: '25px'}}>
            <h2>Measurements</h2>
            <Button variant="contained" onClick={handleOpen}>New measurement</Button>
            <br/>
            <br />
            <MeasurementsTable measurements={measurements}/>
            <AddMeasurement open={open} handleClose={handleClose}/>
        </div>
    );
}

export default Measurements;