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
import AddNoteComponent from "./components/AddNoteComponent";
import PublicNotes from "./pages/PublicNotes";

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
							<Route path="/personal-notes" element={<Notes />}></Route>
							<Route path="/public-notes" element={<PublicNotes />}></Route>
							<Route path="/add-note" element={<AddNoteComponent />}></Route>
						</Route>
						<Route path="*" element={<Navigate to="/" replace />} />
					</Routes>
				</AuthProvider>
			</BrowserRouter>
		</>
	);
}

export default App;
