document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const rememberMeCheckbox = document.getElementById('rememberMe');
    const loginBtn = document.getElementById('loginBtn');
    const btnText = loginBtn.querySelector('.btn-text');
    const loading = loginBtn.querySelector('.loading');

    // 页面加载时检查是否有保存的用户名
    loadSavedCredentials();

    // 表单提交事件
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();

        // 清除之前的错误提示
        clearErrors();

        // 验证表单
        if (!validateForm()) {
            return;
        }

        // 执行登录
        handleLogin();
    });

    // 输入框失去焦点时验证
    usernameInput.addEventListener('blur', function() {
        validateUsername();
    });

    passwordInput.addEventListener('blur', function() {
        validatePassword();
    });

    // 输入时清除错误状态
    usernameInput.addEventListener('input', function() {
        this.classList.remove('error');
        document.getElementById('usernameError').textContent = '';
    });

    passwordInput.addEventListener('input', function() {
        this.classList.remove('error');
        document.getElementById('passwordError').textContent = '';
    });

    /**
     * 验证表单
     */
    function validateForm() {
        let isValid = true;

        if (!validateUsername()) {
            isValid = false;
        }

        if (!validatePassword()) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * 验证用户名
     */
    function validateUsername() {
        const username = usernameInput.value.trim();

        if (!username) {
            showError('username', '请输入用户名');
            return false;
        }

        if (username.length < 3) {
            showError('username', '用户名至少3个字符');
            return false;
        }

        return true;
    }

    /**
     * 验证密码
     */
    function validatePassword() {
        const password = passwordInput.value;

        if (!password) {
            showError('password', '请输入密码');
            return false;
        }

        if (password.length < 6) {
            showError('password', '密码至少6个字符');
            return false;
        }

        return true;
    }

    /**
     * 显示错误信息
     */
    function showError(field, message) {
        const input = document.getElementById(field);
        const errorElement = document.getElementById(field + 'Error');

        input.classList.add('error');
        errorElement.textContent = message;
    }

    /**
     * 清除错误信息
     */
    function clearErrors() {
        const inputs = document.querySelectorAll('.form-group input');
        const errors = document.querySelectorAll('.error-message');

        inputs.forEach(input => input.classList.remove('error'));
        errors.forEach(error => error.textContent = '');
    }

    /**
     * 处理登录
     */
    async function handleLogin() {
        const username = usernameInput.value.trim();
        const password = passwordInput.value;
        const rememberMe = rememberMeCheckbox.checked;

        // 显示加载状态
        setLoading(true);

        try {
            const response = await fetch('/admin/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            });

            const result = await response.json();

            if (response.ok && result.status === 'success') {
                // 登录成功
                showSuccess('登录成功，正在跳转...');

                // 如果选择了"记住我"，保存用户名
                if (rememberMe) {
                    localStorage.setItem('rememberedUsername', username);
                } else {
                    localStorage.removeItem('rememberedUsername');
                }

                // 延迟跳转到后台首页
                setTimeout(() => {
                    window.location.href = '/admin/dashboard';
                }, 1000);
            } else {
                // 登录失败
                showError('password', result.info || '用户名或密码错误');
            }
        } catch (error) {
            console.error('登录失败:', error);
            showError('password', '网络错误，请稍后重试');
        } finally {
            setLoading(false);
        }
    }

    /**
     * 设置加载状态
     */
    function setLoading(isLoading) {
        if (isLoading) {
            loginBtn.disabled = true;
            btnText.style.display = 'none';
            loading.style.display = 'inline-flex';
        } else {
            loginBtn.disabled = false;
            btnText.style.display = 'inline';
            loading.style.display = 'none';
        }
    }

    /**
     * 显示成功提示
     */
    function showSuccess(message) {
        // 可以添加一个成功提示框
        const successDiv = document.createElement('div');
        successDiv.className = 'success-message';
        successDiv.textContent = message;
        successDiv.style.cssText = `
            position: fixed;
            top: 20px;
            left: 50%;
            transform: translateX(-50%);
            background: #67c23a;
            color: white;
            padding: 12px 24px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            z-index: 1000;
            animation: slideDown 0.3s ease-out;
        `;

        document.body.appendChild(successDiv);

        setTimeout(() => {
            successDiv.remove();
        }, 2000);
    }

    /**
     * 加载保存的凭证
     */
    function loadSavedCredentials() {
        const rememberedUsername = localStorage.getItem('rememberedUsername');
        if (rememberedUsername) {
            usernameInput.value = rememberedUsername;
            rememberMeCheckbox.checked = true;
            passwordInput.focus();
        } else {
            usernameInput.focus();
        }
    }
});
