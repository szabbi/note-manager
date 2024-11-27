import { React, useContext } from "react";
import { logout } from "../services/UserService";
import { AuthContext } from "../components/AuthProvider";
import { Link } from "react-router-dom";

const HeaderComponent = () => {
	const { isAuthenticated, logoutUserContext } = useContext(AuthContext);

	const handleLogout = async () => {
		try {
			await logout();
			logoutUserContext();
		} catch (error) {
			console.log(error);
		}
	};

	return (
		<div>
			<header>
				<nav className="navbar navbar-dark navbar-expand-lg bg-secondary">
					<a className="navbar-brand m-1" href="#">
						Note Manager
					</a>

					{isAuthenticated && (
						<>
							<button
								className="navbar-toggler"
								type="button"
								data-bs-toggle="collapse"
								data-bs-target="#navbarNav"
								aria-controls="navbarNav"
								aria-expanded="false"
								aria-label="Toggle navigation">
								<span className="navbar-toggler-icon"></span>
							</button>
							<div className="collapse navbar-collapse" id="navbarNav">
								<ul className="navbar-nav mr-auto">
									<li className="nav-item">
										<Link className="nav-link" to={"/personal-notes"}>
											Personal Notes
										</Link>
									</li>
									<li className="nav-item">
										<Link className="nav-link" to={"/public-notes"}>
											Public Notes
										</Link>
									</li>
								</ul>
								<div className="ms-auto">
									<button type="submit" className="btn btn-success" onClick={() => handleLogout()}>
										Logout
									</button>
								</div>
							</div>
						</>
					)}
				</nav>
			</header>
		</div>
	);
};

export default HeaderComponent;
