import { Route, Routes, Navigate } from "react-router-dom";
import BoardList from "./routes/BoardList";
import Home from "./routes/Home";
import BoardWrite from "./routes/BoardWrite";
import MemberLogin from "./routes/MemberLogin";
import MemberJoin from "./routes/MemberJoin";
import Webchat from "./routes/ChatRoomDetail";
import MakeChat from "./routes/MakeChat";
import BoardDetail from "./routes/BoardDetail";
import ChatRoomList from "./routes/ChatRoomList";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" replace />} />
      <Route path="/home" element={<Home />} />
      <Route path="/board" element={<BoardList />} />
      <Route path="/board/:boardId" element={<BoardDetail />} />
      <Route path="/write" element={<BoardWrite />} />
      <Route path="/login" element={<MemberLogin />} />
      <Route path="/join" element={<MemberJoin />} />
      <Route path="/chat" element={<ChatRoomList />} />
      <Route path="/chat/make" element={<MakeChat />} />
      <Route path="/chat/chatroom" element={<Webchat />} />
      <Route path="/chat/chatroom/:chatRoomId" element={<Webchat />} />
    </Routes>
  );
}

export default App;
