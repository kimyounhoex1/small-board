import React from "react";
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();

  const moveToLogin = () => {
    navigate('/login');
  };

  const moveToJoin = () => {
    navigate('/join');
  };

  return (
    <div className="bg-white-100 min-h-screen flex justify-center items-center">
      <div className="bg-white rounded-2xl shadow-2xl p-10 max-w-md w-full text-center space-y-6">
        <h1 className="text-3xl font-bold text-gray-800">호 무 위 키</h1>
        <p className="text-gray-600">웰 컴 마 이 에 어 리 어</p>
        <div className="flex justify-center gap-4">
          <button
            onClick={moveToLogin}
            className="px-6 py-2 bg-blue-500 text-white font-semibold rounded hover:bg-blue-600 transition"
          >
            로그인
          </button>
          <button
            onClick={moveToJoin}
            className="px-6 py-2 bg-green-500 text-white font-semibold rounded hover:bg-green-600 transition"
          >
            회원가입
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;
