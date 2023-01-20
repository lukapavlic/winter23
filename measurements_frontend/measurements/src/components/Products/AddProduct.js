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

export default function AddProduct({ open, handleClose }) {
    const [name, setName] = React.useState("");
    const [maxMeasure, setMaxMeasure] = React.useState("");
    const [minMeasure, setMinMeasure] = React.useState("");
    const measurementsApi = new MeasurementsApi();
    const addProduct = async () => {
        const result = await measurementsApi.postProduct(name, maxMeasure, minMeasure);
        if (result.request.status === 200) {
            handleClose();
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
                        Add new product
                    </Typography>
                    <TextField
                        required
                        id="name"
                        label="product name"
                        value={name}
                        onChange={(event) => { setName(event.target.value) }}
                    />
                    <TextField
                        required
                        id="max measure"
                        label="Max measure"
                        value={maxMeasure}
                        onChange={(event) => { setMaxMeasure(event.target.value) }}
                    />
                    <TextField
                        required
                        id="min measure"
                        label="Min measure"
                        value={minMeasure}
                        onChange={(event) => { setMinMeasure(event.target.value) }}
                    />
                    <div>
                        <Button variant="contained"
                            onClick={() => {
                                addProduct();
                                setName("");
                                setMaxMeasure("");
                                setMinMeasure("");
                            }}>
                            Add
                        </Button>
                    </div>
                </Box>
            </Modal>
        </div>
    );
}