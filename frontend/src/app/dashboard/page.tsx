"use client";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

type User = {
  username: string;
  email: string;
  firstName: string;
  lastName: string;
};

const Dashboard = () => {
  const router = useRouter();
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    console.log("Token found in localStorage:", token);
    if (!token) {
      router.push("/login");
      return;
    }
    fetchUserData(token);
  }, []);
  

  const fetchUserData = async (token: string) => {
    try {
      const res = await fetch("https://fitnesstracker-jzqd.onrender.com/users/me", {
        method: "GET",
        headers: { Authorization: `Bearer ${token}` },
      });
      const data = await res.json();
      if (res.ok) {
        setUser(data);
      } else {
        router.push("/login");
      }
    } catch (error) {
      console.error("Error fetching user:", error);
      router.push("/login");
    }
  };

  if (!user) return <p>Loading...</p>;

  return (
    <div className="min-h-screen flex items-center justify-center">
      <h2>Welcome, {user.username}!</h2>
    </div>
  );
};

export default Dashboard;
