import React, { createContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
	const [authState, setAuthState] = useState({
		isAuthenticated: !!localStorage.getItem("token"),
		token: localStorage.getItem("token"),
	});

	const loginUserContext = (token) => {
		setAuthState({ isAuthenticated: true, token });
		localStorage.setItem("token", token);
	};

	const logoutUserContext = () => {
		setAuthState({ isAuthenticated: false, token: null });
		localStorage.removeItem("token");
	};

	return (
		<AuthContext.Provider
			value={{
				isAuthenticated: authState.isAuthenticated,
				loginUserContext,
				logoutUserContext,
			}}>
			{children}
		</AuthContext.Provider>
	);
};
