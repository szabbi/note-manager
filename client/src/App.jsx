import "./App.css";
import HeaderComponent from "./components/HeaderComponent";
import ListUserCompontent from "./components/ListUserCompontent";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import UserComponent from "./components/UserComponent";

function App() {
	return (
		<>
			<BrowserRouter>
				<HeaderComponent />
				<Routes>
					<Route path="/" element={<ListUserCompontent />}></Route>
					<Route
						path="/users"
						element={<ListUserCompontent />}
					></Route>
					<Route path="/add-user" element={<UserComponent />}></Route>
				</Routes>
			</BrowserRouter>
		</>
	);
}

export default App;
