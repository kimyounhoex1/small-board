import { Route, Routes, Navigate } from "react-router-dom";
import BoardList from "./routes/BoardList";
import Home from "./routes/Home";
import BoardWrite from "./routes/BoardWrite";
import MemberLogin from "./routes/MemberLogin";
import MemberJoin from "./routes/MemberJoin";
import Webchat from "./routes/Webchat";
import React from "react";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" replace />} />
      <Route path="/home" element={<Home />} />
      <Route path="/board" element={<BoardList />} />
      <Route path="/write" element={<BoardWrite />} />
      <Route path="/login" element={<MemberLogin />} />
      <Route path="/join" element={<MemberJoin />} />      
      <Route path="/chat" element={<Webchat />} />
    </Routes>
  );
}

export default App;
