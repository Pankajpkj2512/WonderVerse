import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import ProtectedRoute from "./components/common/ProtectedRoute";
import Navbar from "./components/layout/Navbar";
import Landing from "./pages/Landing";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import ParentDashboard from "./pages/parent/ParentDashboard";
import ManageChildren from "./pages/parent/ManageChildren";
import ChildDashboard from "./pages/child/ChildDashboard";
import AdminDashboard from "./pages/admin/AdminDashboard";
import "./styles/global.css";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route
            path="/parent"
            element={
              <ProtectedRoute allowedRoles={["PARENT"]}>
                <ParentDashboard />
              </ProtectedRoute>
            }
          />
          <Route
            path="/parent/children"
            element={
              <ProtectedRoute allowedRoles={["PARENT"]}>
                <ManageChildren />
              </ProtectedRoute>
            }
          />
          <Route
            path="/child"
            element={
              <ProtectedRoute allowedRoles={["CHILD"]}>
                <ChildDashboard />
              </ProtectedRoute>
            }
          />
          <Route
            path="/admin"
            element={
              <ProtectedRoute allowedRoles={["ADMIN"]}>
                <AdminDashboard />
              </ProtectedRoute>
            }
          />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
