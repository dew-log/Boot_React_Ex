import { useState } from "react";
import { SERVER_URL } from "./constants";
import { Button, TextField, Stack } from "@mui/material";

const Login = ()=>{

    return (
        <div>
            <Stack spacing={2} allgnItems="center" mt={2}>
                <TextField name="username" label="username" />
                <TextField name="password" label="password" />
                <Button variant="outlined" color="primary">Login</Button>
            </Stack>
        </div>
    )
}

export default Login;