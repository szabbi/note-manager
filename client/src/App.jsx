import "./App.css";
import HeaderComponent from "./components/HeaderComponent";
import Registration from "./pages/Registration";
import Login from "./pages/Login";
import { Routes, Route, BrowserRouter, Navigate } from "react-router-dom";
import React from "react";
import Notes from "./pages/Notes";
import ProtectedRoutes from "./utils/ProtectedRoutes";
import PublicRoutes from "./utils/PublicRoutes";
import { AuthProvider } from "./components/AuthProvider";

function App() {
	return (
		<>
			<BrowserRouter>
				<AuthProvider>
					<HeaderComponent />
					<Routes>
						<Route element={<PublicRoutes />}>
							<Route path="/" element={<Login />}></Route>
							<Route path="/registration" element={<Registration />}></Route>
						</Route>

						<Route element={<ProtectedRoutes />}>
							<Route path="/notes" element={<Notes />}></Route>
						</Route>
					</Routes>
				</AuthProvider>
			</BrowserRouter>
		</>
	);
}

export default App;
