import './App.css';
import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import NavBar from "./components/NavBar/NavBar";
import LogIn from "./components/LogIn";
import Care from "./components/Care";
import {Home} from "./components/Home";
import Train from "./components/Train";
import {SignUp} from "./components/SignUp";
import Logged from "./components/Logged";
import UserProfile from "./components/UserProfile";

const App = () =>  {
  return (
    <Router>
      <NavBar />
      <Routes>
          <Route path="/*" element={<Home />} />
          <Route path="train/*" element={<Train />} />
          <Route path="care/*" element={<Care />} />
          <Route path="login/*" element={<LogIn />} />
          <Route path="signup/*" element={<SignUp />} />
          <Route path="logged/*" element={<Logged />} />
          <Route path="signed/*" element={<SignUp />} />
          <Route path="userprofile/*" element={<UserProfile />} />
      </Routes>
    </Router>
  );
}

export default App;
