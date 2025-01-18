import React, { useState, useEffect } from 'react';
import axios from 'axios';

function GoogleLogin() {
    const [userName, setUserName] = useState('');
    const [userInfo, setUserInfo] = useState(null);

    const apiClient = axios.create({
        baseURL: 'http://localhost:8080', // API 기본 URL
        withCredentials: true,           // 쿠키를 포함한 요청
    });


    useEffect(() => {
        // 페이지 로드 시 사용자 이름을 설정 (서버에서 실제 사용자 이름을 받아오는 로직)
        setUserName('사용자'); // 실제로는 서버에서 사용자 이름을 받아와야 합니다.
    }, []);

    const logout = () => {
        // Spring Security의 로그아웃 엔드포인트로 리다이렉션
        window.location.href = '/logout';
    };

    const fetchUserInfo = async () => {
        try {
            const response = await axios.get('http://localhost:8080/members',
                {
                    withCredentials: true,
                });
            setUserInfo(response.data);
        } catch (err) {
            console.error('Error:', err);
            alert('사용자 정보를 가져오는 데 실패했습니다.');
        }
    };

    const loginWithGoogle = () => {
        // Spring Security OAuth2 엔드포인트로 리디렉션
        window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    };

    return (
        <div>
            {/* 로그인 폼 */}
            <div className="login-card">
                <h1>로그인</h1>
                <p>Google 계정으로 로그인하세요.</p>
                <button className="google-btn" onClick={loginWithGoogle}>
                    <span className="google-icon"></span> Google로 로그인
                </button>
                <p className="footer">로그인하면 서비스 이용약관에 동의하는 것으로 간주됩니다.</p>
            </div>

            {/* 로그인 후 사용자 정보 */}
            {userName && (
                <div className="welcome-card">
                    <h1>안녕하세요, {userName}님</h1>
                    <button id="logout-btn" className="btn" onClick={logout}>로그아웃</button>
                    <button id="info-btn" className="btn" onClick={fetchUserInfo}>인증 정보 조회</button>
                    {userInfo && (
                        <div id="user-info">
                            <h3>사용자 정보:</h3>
                            <p>이름: {userInfo.name}</p>
                            <p>이메일: {userInfo.email}</p>
                            <p>ID 토큰: {userInfo.uuid}</p>
                            <p>role: {userInfo.role}</p>
                            <img src={userInfo.artistImage} alt="User Avatar" />
                        </div>
                    )}
                    {profileInfo && (
                        <div id="user-info">
                            <h3>프로필 정보:</h3>
                            <p>이름: {profileInfo.name}</p>
                            <img src={profileInfo.profileImage} alt="User Avatar" />
                        </div>
                    )}
                </div>
            )}
        </div>
    );
}

export default GoogleLogin;
