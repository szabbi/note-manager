import React, { useContext, useState } from "react";
import { login } from "../services/UserService";
import { ToastContainer, toast } from "react-toastify";
import { AuthContext } from "../components/AuthProvider";

const Login = () => {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const { loginUserContext } = useContext(AuthContext);

	function loginUser(e) {
		e.preventDefault();
		const user = { email, password };

		login(user)
			.then((response) => {
				toast.success("Login successful!");
				loginUserContext(response.data);
			})
			.catch((error) => {
				toast.error(error.response.data);
			});
	}

	return (
		<div className="container mt-5">
			<div className="row">
				<div className="card col-md-6 offset-md-3">
					<h2 className="text-center">Login</h2>
					<div className="card-body">
						<form className="row g-2">
							<div className="form-group mb-2">
								<label className="form-label">Email</label>
								<input
									type="email"
									placeholder="Enter user email"
									name="email"
									value={email}
									className="form-control"
									onChange={(e) => setEmail(e.target.value)}
									required
								/>
							</div>
							<div className="form-group mb-2">
								<label className="form-label">Password</label>
								<input
									type="password"
									placeholder="Enter user password"
									name="password"
									value={password}
									className="form-control"
									onChange={(e) => setPassword(e.target.value)}
									required
								/>
							</div>
							<button type="submit" className="btn btn-success" onClick={loginUser}>
								Submit
							</button>
						</form>
					</div>
					<a className="link-primary link-underline-opacity-0" href="/registration">
						Don&apos;t have an account?
					</a>
				</div>
			</div>
			<ToastContainer />
		</div>
	);
};

export default Login;
