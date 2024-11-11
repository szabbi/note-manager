import React, { useEffect, useState } from "react";
import { listUsers } from "../services/UserService";

const ListUserCompontent = () => {
	const [users, setUsers] = useState([]);
	useEffect(() => {
		listUsers()
			.then((response) => {
				setUsers(response.data);
			})
			.catch((error) => {
				console.log(error);
			});
	}, []);
	return (
		<div className="container">
			<h1>List of users</h1>
			<table className="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
					</tr>
				</thead>
				<tbody>
					{users.map((user) => (
						<tr key={user.id}>
							<td>{user.id}</td>
							<td>{user.name}</td>
							<td>{user.email}</td>
							<td>{user.password}</td>
						</tr>
					))}
				</tbody>
			</table>
		</div>
	);
};

export default ListUserCompontent;
