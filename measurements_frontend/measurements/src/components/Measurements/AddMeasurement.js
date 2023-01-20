import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { TextField } from '@mui/material';
import { MeasurementsApi } from '../../api/api';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export default function AddMeasurement({ open, handleClose }) {
    const [productId, setProductId] = React.useState("");
    const [avgTemp, setAvgTemp] = React.useState("");
    const measurementsApi = new MeasurementsApi();
    const addMeasurement = async () => {
        const result = await measurementsApi.postMeasurement(productId, avgTemp);
        if (result.request.status === 200) {
            handleClose();
            setProductId("");
            setAvgTemp("");
        }
    }

    return (
        <div>

            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Add new measurement
                    </Typography>
                    <TextField
                        required
                        id="productId"
                        label="product ID"
                        value={productId}
                        onChange={(event) => { setProductId(event.target.value) }}
                    />
                    <TextField
                        required
                        id="avgTemp"
                        label="average temperature"
                        value={avgTemp}
                        onChange={(event) => { setAvgTemp(event.target.value) }}
                    />
                    <div>
                        <Button variant="contained" onClick={addMeasurement}>Add</Button>
                    </div>
                </Box>
            </Modal>
        </div>
    );
}