import { Button, CircularProgress } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { MeasurementsApi } from '../../api/api';
import AddMeasurement from './AddMeasurement';
import MeasurementsTable from './MeasurementsTable';

const Measurements = () => {
    const [measurements, setMeasurements] = useState([]);
    const [open, setOpen] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const measurementsApi = new MeasurementsApi();
    const fetchData = async () => {
        const result = await measurementsApi.getHistory();
        setMeasurements(result.data);
        setLoaded(true);
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
        <div style={{ padding: '25px' }}>
            <h2>Measurements</h2>
            <Button variant="contained" onClick={handleOpen}>New measurement</Button>
            <br />
            <br />
            {loaded ? <MeasurementsTable measurements={measurements} /> : <CircularProgress />}
            <AddMeasurement open={open} handleClose={handleClose} />
        </div>
    );
}

export default Measurements;