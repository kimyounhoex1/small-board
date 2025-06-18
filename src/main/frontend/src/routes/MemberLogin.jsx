import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const MemberLogin = () => {
  const navigate = useNavigate();

  // const isLoggedIn = () =>{
  //   const [isLoged]
  // }

  const [loginData, setLoginData] = useState({
    nickname: "",
    password: ""
  });

  const onChange = (e) => {
    const { name, value } = e.target;
    setLoginData({ ...loginData, [name]: value });
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/member/login", loginData);
      
      const { accessToken, refreshToken, nickname } = response.data;

      // 토큰 저장 (localStorage)
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      localStorage.setItem("nickname", nickname);

      alert("로그인 성공!");
      navigate("/board");
    } catch (err) {
      alert("로그인 실패");
      console.error(err);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4">
      <div className="w-full max-w-md bg-white p-8 rounded-xl shadow-lg space-y-6">
        <h2 className="text-2xl font-bold text-gray-800 text-center">🔐 로그인</h2>

        <div>
          <label className="block text-sm font-medium text-gray-700">닉네임</label>
          <input
            type="text"
            name="nickname"
            value={loginData.nickname}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">비밀번호</label>
          <input
            type="password"
            name="password"
            value={loginData.password}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div className="flex justify-between mt-6">
          <button
            onClick={handleLogin}
            className="px-6 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            로그인
          </button>
          <button
            onClick={() => navigate("/join")}
            className="px-6 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
          >
            회원가입
          </button>
        </div>
      </div>
    </div>
  );
};

export default MemberLogin;
