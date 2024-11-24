import { React, useContext } from "react";
import { Outlet, Navigate } from "react-router-dom";
import { AuthContext } from "../components/AuthProvider";

const PublicRoutes = () => {
	const { isAuthenticated } = useContext(AuthContext);

	return isAuthenticated ? <Navigate to="/personal-notes" replace /> : <Outlet />;
};

export default PublicRoutes;
