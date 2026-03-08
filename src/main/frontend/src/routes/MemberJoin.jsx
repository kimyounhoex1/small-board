import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const MemberJoin = () => {
  const navigate = useNavigate();
  const [member, setMember] = useState({
    name: "",
    nickname: "",
    password: "",
    age: "",
  });

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      navigate("/home");
    }
  }, [navigate]);

  const onChange = (e) => {
    const { name, value } = e.target;
    setMember({ ...member, [name]: value });
  };

  const joinMember = async () => {
    try {
      await axios.post("http://localhost:8080/api/member/join", member);
      alert("🎉 회원가입 완료!");
      navigate("/login");
    } catch (err) {
      alert("회원가입 실패 🥲");
      console.error(err);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4">
      <div className="w-full max-w-xl bg-white p-8 rounded-xl shadow-lg space-y-6">
        <h2 className="text-2xl font-bold text-gray-800 text-center">
          🙋 회원가입
        </h2>

        <div>
          <label className="block text-sm font-medium text-gray-700">
            이름
          </label>
          <input
            type="text"
            name="name"
            value={member.name}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">
            닉네임
          </label>
          <input
            type="text"
            name="nickname"
            value={member.nickname}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">
            비밀번호
          </label>
          <input
            type="password"
            name="password"
            value={member.password}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">
            나이
          </label>
          <input
            type="number"
            name="age"
            value={member.age}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div className="flex justify-between mt-6">
          <button
            onClick={joinMember}
            className="px-6 py-2 bg-green-500 text-white rounded hover:bg-green-600"
          >
            가입하기
          </button>
          <button
            onClick={() => navigate("/login")}
            className="px-6 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
          >
            취소
          </button>
        </div>
      </div>
    </div>
  );
};

export default MemberJoin;
