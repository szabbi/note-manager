import { React, useContext, useState, useEffect } from "react";
import { logout, getUser } from "../services/UserService";
import { AuthContext } from "../components/AuthProvider";
import { Link } from "react-router-dom";

const HeaderComponent = () => {
	const { isAuthenticated, logoutUserContext } = useContext(AuthContext);
	const [name, setName] = useState("");

	const handleLogout = async () => {
		try {
			await logout();
			logoutUserContext();
		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		const fetchUsername = async () => {
			try {
				const response = await getUser();
				console.log(response.data);

				setName(response.data);
				console.log("asd:", { name });
			} catch (error) {
				console.log(error);
			}
		};

		fetchUsername();
	}, []);

	return (
		<header className="bg-primary sticky-top">
			<div className="container-fluid">
				<nav className="navbar navbar-expand-lg navbar-dark bg-primary">
					<a className="navbar-brand fw-bold" href="#">
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
								<ul className="navbar-nav me-auto mb-2 mb-lg-0">
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
								<div className="d-flex flex-column align-items-end">
									<p className="text-primary mb-0">{name}</p>
									<button type="submit" className="btn btn-danger fw-bold mt-0" onClick={() => handleLogout()}>
										Logout
									</button>
								</div>
							</div>
						</>
					)}
				</nav>
			</div>
		</header>
	);
};

export default HeaderComponent;
