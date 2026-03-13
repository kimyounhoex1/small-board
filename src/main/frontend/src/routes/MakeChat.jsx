import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom'

const TOKEN_TYPE = localStorage.getItem("nickname");
let ACCESS_TOKEN = localStorage.getItem("accessToken");
let REFRESH_TOKEN = localStorage.getItem("refreshToken");

const MakeChat = () => {
    const navigate = useNavigate();
    
    const [chat, setchat] = useState({
        name: "",
        createByMemberId: "",
        Users: ""
    })

    const onChange = (e) => {
        const { name, value } = e.target;
        setchat({ ...chat, [name]: value });
    };

    const saveChat = async() => {
        try {
            await api.post("api/chat/make", board);
        }

    }
    
}