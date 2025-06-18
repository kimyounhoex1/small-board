import {Route, Routes} from "react-router-dom";
import BoardList from "./routes/BoardList";
import Home from "./routes/Home";
import BoardWrite from "./routes/BoardWrite";
import MemberLogin from "./routes/MemberLogin";
import MemberJoin from "./routes/MemberJoin";
import React from "react";

function App(){
  return (
    <Routes>
      <Route path="/" element={<Home/>}/>                     
      <Route path="/board" element={<BoardList />}/>
      <Route path="/write" element={<BoardWrite />}/>
      <Route path="/login" element={<MemberLogin />}/>
      <Route path="/join" element={<MemberJoin />}/>
    </Routes>
  )
}

export default App;
