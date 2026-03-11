import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

const WS_URL = "http://localhost:8080/ws";

export function createStompClient({ accessToken, onConnect, onError }) {
  const client = new Client({
    webSocketFactory: () => new SockJS(WS_URL),
    connectHeaders: accessToken
      ? { Authorization: `Bearer ${accessToken}` }
      : {},
    debug: (str) => console.log(str),
    reconnectDelay: 3000,
    onConnect,
    onStompError: (frame) => {
      console.error("Broker error", frame.body);
      onError?.(frame);
    },
  });
  return client;
}
