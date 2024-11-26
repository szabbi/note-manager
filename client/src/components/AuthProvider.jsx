import React, { createContext, useState } from "react";

export const AuthContext = createContext();

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
	const [authState, setAuthState] = useState({
		isAuthenticated: !!sessionStorage.getItem("token"),
		token: sessionStorage.getItem("token"),
	});

	const loginUserContext = (token) => {
		setAuthState({ isAuthenticated: true, token });
		sessionStorage.setItem("token", token);
	};

	const logoutUserContext = () => {
		setAuthState({ isAuthenticated: false, token: null });
		sessionStorage.removeItem("token");
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
