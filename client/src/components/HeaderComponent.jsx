import { React, useContext } from "react";
import { logout } from "../services/UserService";
import { AuthContext } from "../components/AuthProvider";
import { ToastContainer, toast } from "react-toastify";

const HeaderComponent = () => {
	const { isAuthenticated, logoutUserContext } = useContext(AuthContext);

	function handleLogout() {
		try {
			logout().then((respone) => {
				logoutUserContext();
				toast.success("Logout successful!");
			});
		} catch (error) {
			console.error("Logout failed:", error);
		}
	}

	return (
		<div>
			<header>
				<nav className="navbar navbar-dark bg-dark">
					<a className="navbar-brand" href="#">
						Note Manager
					</a>
					{isAuthenticated && (
						<button type="submit" className="btn btn-success" onClick={handleLogout}>
							Logout
						</button>
					)}
				</nav>
			</header>
		</div>
	);
};

export default HeaderComponent;
