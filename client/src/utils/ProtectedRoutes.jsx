import { React, useContext } from "react";
import { Outlet, Navigate } from "react-router-dom";
import { AuthContext } from "../components/AuthProvider";

const ProtectedRoutes = () => {
	const { isAuthenticated } = useContext(AuthContext);
	return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
};

export default ProtectedRoutes;
