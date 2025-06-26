import { useState } from "react";
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import { Button, TextField, Stack } from "@mui/material";

const EditCar = (props) => {
  const { data, updateCar } = props;

  const [open, setOpen] = useState(false);
  const [car, setCar] = useState({
    brand: "",
    model: "",
    color: "",
    registrationNumber: "",
    modelYear: "",
    price: "",
  });

  const handleClickOpen = () => {
    setCar({
      brand: data.row.brand,
      model: data.row.model,
      color: data.row.color,
      registrationNumber: data.row.registrationNumber,
      modelYear: data.row.modelYear,
      price: data.row.price
    });
    setOpen(true);
  };
  const handleClickClose = () => setOpen(false);
  const handleChange = (e) =>
    setCar({ ...car, [e.target.name]: e.target.value });
  const handleSave = () => {
    updateCar(car, data.id);
    handleClickClose();
  };

  return (
    <div>
      <button onClick={handleClickOpen}>Edit Car</button>
      <Dialog open={open} onClose={handleClickClose}>
        <DialogTitle>Edit Car</DialogTitle>
        <DialogContent>
          <Stack spacing={2} mt={1}>
            <input
              placeholder="Brand"
              name="brand"
              value={car.brand}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Model"
              name="model"
              value={car.model}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Color"
              name="color"
              value={car.color}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="RegNum"
              name="registrationNumber"
              value={car.registrationNumber}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="ModelYear"
              name="modelYear"
              value={car.modelYear}
              onChange={handleChange}
            />
            <br />
            <input
              placeholder="Price"
              name="price"
              value={car.price}
              onChange={handleChange}
            />
            <br />
          </Stack>
        </DialogContent>
        <DialogActions>
          <button onClick={handleClickClose}>Cancel</button>
          <button onClick={handleSave}>Save</button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default EditCar;