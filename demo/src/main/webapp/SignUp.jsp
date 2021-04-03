<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<div class="toaster"></div>

<main class="wrapper">
  <section class="section-form">
    <h1>회원가입</h1>

    <form name="signup" class="form" novalidate>
      <div class="form-group">
        <label for="name" class="form-label form-label--required">
          	이름:
        </label>

        <div class="form-addon" data-states-for="name">
          <div class="form-addon__addon">
            <span class="icon-name"></span>
          </div>

          <input type="text"
                 id="name"
                 name="name"
                 class="form-input"
                 placeholder="Andrew"
                 required>
          
          <span class="form-addon__icon icon-valid"></span>
          <span class="form-addon__icon icon-invalid"></span>
        </div>

        <div data-errors-for="name">
          <small class="form-error"
                 data-errors-when="valueMissing">
            	기입해야하는 항목입니다.
          </small>
        </div>
      </div>

      <div class="form-group">
        <label for="email" class="form-label form-label--required">
          Email:
        </label>

        <div class="form-addon" data-states-for="email">
          <div class="form-addon__addon">
            <span class="icon-email"></span>
          </div>

          <input type="email"
                 id="email"
                 name="email"
                 class="form-input"
                 placeholder="andrew@email.com"
                 required>

          <span class="form-addon__icon icon-valid"></span>
          <span class="form-addon__icon icon-invalid"></span>
        </div>

        <div data-errors-for="email">
          <small class="form-error"
                 data-errors-when="valueMissing">
            	반드시 기입해야하는 항목입니다.
          </small>
          
          <small class="form-error"
                 data-errors-when="typeMismatch">
            Please enter a valid email address.
          </small>
        </div>
      </div>

      <div class="form-group">
        <label for="phone" class="form-label">
          	개인 핸드폰 번호:
        </label>

        <div class="form-addon" data-states-for="phone">
          <div class="form-addon__addon">
            <span class="icon-phone"></span>
          </div>

          <input type="text"
                 id="phone"
                 name="phone"
                 class="form-input"
                 placeholder="000-0000-0000"
                 pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}">

          <span class="form-addon__icon icon-valid"></span>
          <span class="form-addon__icon icon-invalid"></span>
        </div>

        <div data-errors-for="phone">
          <small class="form-error"
                 data-errors-when="patternMismatch">
            000-0000-0000 패턴 형식에 맞는 번호를 입력해주세요
          </small>
        </div>
      </div>

      <div class="form-group">
        <label for="password" class="form-label form-label--required">
          	비밀번호 <span class="text-muted">(minimum 6 characters)</span>:
        </label>

        <div class="form-addon" data-states-for="password">
          <div class="form-addon__addon">
            <span class="icon-password"></span>
          </div>

          <input type="password"
                 id="password"
                 name="password"
                 class="form-input"
                 placeholder="********"
                 minlength="6"
                 required>

          <span class="form-addon__icon icon-valid"></span>
          <span class="form-addon__icon icon-invalid"></span>
        </div>

        <div data-errors-for="password">
          <small class="form-error"
                 data-errors-when="valueMissing">
           	 반드시 기입해야하는 항목입니다.
          </small>
          
          <small class="form-error"
                 data-errors-when="tooShort">
            	최소 6글자 이상이여야 합니다.
          </small>
        </div>
      </div>

      <div class="form-footer">
        <button type="submit" class="button">가입요청</button>
      </div>
    </form>
  </section>
</main>

</body>
</html>