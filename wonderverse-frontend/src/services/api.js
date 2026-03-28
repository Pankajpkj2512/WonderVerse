import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: { "Content-Type": "application/json" },
});

// Attach JWT token to every request
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Handle 401 - redirect to login
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.clear();
      window.location.href = "/login";
    }
    return Promise.reject(error);
  },
);

// ─── Auth ───────────────────────────────────────────────
export const authAPI = {
  register: (data) => api.post("/auth/register", data),
  login: (data) => api.post("/auth/login", data),
};

// ─── Videos ─────────────────────────────────────────────
export const videoAPI = {
  getAll: (params) => api.get("/videos", { params }),
  getById: (id) => api.get(`/videos/${id}`),
  upload: (data) => api.post("/admin/videos", data),
  delete: (id) => api.delete(`/admin/videos/${id}`),
};

// ─── Child Profiles ──────────────────────────────────────
export const childAPI = {
  create: (data) => api.post("/parent/child", data),
  getAll: () => api.get("/parent/children"),
  update: (id, data) => api.put(`/parent/child/${id}`, data),
  delete: (id) => api.delete(`/parent/child/${id}`),
};

// ─── Watch History ───────────────────────────────────────
export const historyAPI = {
  add: (data) => api.post("/history", data),
  getByChild: (childId) => api.get(`/history/${childId}`),
};

// ─── Subscriptions ───────────────────────────────────────
export const subscriptionAPI = {
  get: () => api.get("/subscription"),
  subscribe: (data) => api.post("/subscription", data),
};

export default api;
