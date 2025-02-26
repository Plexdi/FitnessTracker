'use client';

import { useState } from 'react';
import { signIn } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import { motion } from 'framer-motion';

export default function LoginPage() {
  const router = useRouter();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError(null);
    
    // Simulating authentication (Replace with actual API call)
    const res = await signIn('credentials', { email, password, redirect: false });
    if (res?.error) {
      setError('Invalid credentials');
    } else {
      router.push('/dashboard');
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-900">
      <motion.div
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="w-full max-w-md p-8 space-y-4 bg-gray-800 shadow-lg rounded-xl"
      >
        <h2 className="text-2xl font-semibold text-center text-white">Login</h2>
        {error && <p className="text-red-500 text-center">{error}</p>}
        <form className="space-y-4" onSubmit={handleSubmit}>
          <div>
            <label className="text-gray-400">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full p-2 mt-1 bg-gray-700 text-white rounded"
              required
            />
          </div>
          <div>
            <label className="text-gray-400">Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full p-2 mt-1 bg-gray-700 text-white rounded"
              required
            />
          </div>
          <button
            type="submit"
            className="w-full px-4 py-2 font-semibold text-white bg-blue-600 rounded hover:bg-blue-700"
          >
            Login
          </button>
        </form>
        <div className="text-center text-gray-400">Or login with</div>
        <div className="flex space-x-2">
          <button
            onClick={() => signIn('google')}
            className="w-1/2 p-2 font-semibold text-white bg-red-500 rounded hover:bg-red-600"
          >
            Google
          </button>
          <button
            onClick={() => signIn('github')}
            className="w-1/2 p-2 font-semibold text-white bg-gray-700 rounded hover:bg-gray-800"
          >
            GitHub
          </button>
        </div>
      </motion.div>
    </div>
  );
}
