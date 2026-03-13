import React, { useEffect, useRef, useState } from "react";
import { createStompClient } from "../api/websocket";

const ROOM_ID = "general";

export default function WebChat() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const clientRef = useRef(null);

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    const client = createStompClient({
      accessToken,
      onConnect: () => {
        client.subscribe(`/topic/chat/${ROOM_ID}`, (msg) => {
          const body = JSON.parse(msg.body);
          setMessages((prev) => [...prev, body]);
        });
      },
      onError: () => alert("연결 오류"),
    });

    client.activate();
    clientRef.current = client;

    return () => client.deactivate();
  }, []);

  const send = () => {
    if (!input.trim() || !clientRef.current?.connected) return;
    const payload = { sender: "me", content: input };
    clientRef.current.publish({
      destination: `/app/chat/${ROOM_ID}`,
      body: JSON.stringify(payload),
    });
    setInput("");
  };
  return (
    <div className="p-6 max-w-2xl mx-auto space-y-4">
      <div className="border rounded h-96 p-3 overflow-y-auto bg-white shadow">
        {messages.map((m, i) => (
          <div key={i} className="mb-2">
            <span className="font-semibold mr-2">{m.sender}</span>
            <span>{m.content}</span>
          </div>
        ))}
      </div>
      <div className="flex gap-2">
        <input
          className="flex-1 border rounded px-3 py-2"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && send()}
          placeholder="메시지 입력"
        />
        <button
          className="px-4 py-2 bg-blue-500 text-white rounded"
          onClick={send}
        >
          보내기
        </button>
      </div>
    </div>
  );
}
