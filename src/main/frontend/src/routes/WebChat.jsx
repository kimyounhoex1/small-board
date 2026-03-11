import React, { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { createStompClient } from "../api/websocket";

const ROOM_ID = "general";

export default function WebChat() {
  const navigate = useNavigate();
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const [nickname, setNickname] = useState("");
  const [connected, setConnected] = useState(false);
  const clientRef = useRef(null);

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    const userNickname = localStorage.getItem("nickname");

    // 로그인하지 않으면 로그인 페이지로 이동
    if (!accessToken || !userNickname) {
      navigate("/login");
      return;
    }

    setNickname(userNickname);

    const client = createStompClient({
      accessToken,
      onConnect: () => {
        setConnected(true);
        // 채팅방 구독
        client.subscribe(`/topic/chat/${ROOM_ID}`, (msg) => {
          const body = JSON.parse(msg.body);
          setMessages((prev) => [...prev, body]);
        });
      },
      onError: () => {
        setConnected(false);
        alert("연결 오류");
      },
    });

    client.activate();
    clientRef.current = client;

    return () => {
      if (client && client.connected) {
        client.deactivate();
      }
    };
  }, [navigate]);

  const send = () => {
    if (!input.trim() || !clientRef.current?.connected) {
      alert("연결을 확인해주세요");
      return;
    }
    const payload = { sender: nickname, content: input };
    clientRef.current.publish({
      destination: `/app/chat/${ROOM_ID}`,
      body: JSON.stringify(payload),
    });
    setInput("");
  };
  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-2xl mx-auto">
        <div className="bg-white rounded-lg shadow-lg overflow-hidden">
          {/* 헤더 */}
          <div className="bg-blue-600 text-white p-4">
            <h2 className="text-xl font-bold">채팅방: {ROOM_ID}</h2>
            <p className="text-sm mt-1">사용자: {nickname}</p>
            <p className={`text-xs mt-1 ${connected ? "text-green-300" : "text-red-300"}`}>
              {connected ? "✓ 연결됨" : "✗ 연결 끊김"}
            </p>
          </div>

          {/* 메시지 영역 */}
          <div className="h-96 p-4 overflow-y-auto bg-gray-50 border-b border-gray-200">
            {messages.length === 0 ? (
              <div className="text-center text-gray-400 mt-10">
                채팅 메시지가 없습니다
              </div>
            ) : (
              messages.map((m, i) => (
                <div key={i} className="mb-4 flex">
                  <div className={`flex-shrink-0 ${m.sender === nickname ? "ml-auto" : ""}`}>
                    <div className={`${
                      m.sender === nickname 
                        ? "bg-blue-500 text-white" 
                        : "bg-gray-300 text-gray-800"
                    } rounded-lg p-3 max-w-xs`}>
                      <p className="text-xs font-semibold mb-1">{m.sender}</p>
                      <p className="text-sm">{m.content}</p>
                    </div>
                  </div>
                </div>
              ))
            )}
          </div>

          {/* 입력 영역 */}
          <div className="p-4 bg-white flex gap-2">
            <input
              className="flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={(e) => e.key === "Enter" && send()}
              placeholder="메시지를 입력하세요..."
              disabled={!connected}
            />
            <button
              className={`px-6 py-2 rounded-lg text-white font-medium transition ${
                connected
                  ? "bg-blue-600 hover:bg-blue-700"
                  : "bg-gray-400 cursor-not-allowed"
              }`}
              onClick={send}
              disabled={!connected}
            >
              보내기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
