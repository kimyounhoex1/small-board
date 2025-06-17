import {Route, Routes} from "react-router-dom";
import BoardList from "./routes/BoardList";
import Home from "./routes/Home";
import BoardWrite from "./routes/BoardWrite";
import React from "react";

function App(){
  return (
    <Routes>
      <Route path="/" element={<Home/>}/>                     
      <Route path="/board" element={<BoardList/>}/>
      <Route path="/write" element={<BoardWrite />} />
    </Routes>
  )
}

export default App;
