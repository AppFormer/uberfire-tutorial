<!DOCTYPE html>
<!--[if IE 8]><html class="ie8 login-pf"><![endif]-->
<!--[if IE 9]><html class="ie9 login-pf"><![endif]-->
<!--[if gt IE 9]><!-->
<html class="login-pf">
<!--<![endif]-->
<head>
 <title>Login - PatternFly</title>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="apple-touch-icon-precomposed" sizes="144x144" href="UberFireTutorial/patternfly/dist/img/apple-touch-icon-144-precomposed.png">
 <link rel="apple-touch-icon-precomposed" sizes="114x114" href="UberFireTutorial/patternfly/dist/img/apple-touch-icon-114-precomposed.png">
 <link rel="apple-touch-icon-precomposed" sizes="72x72" href="UberFireTutorial/patternfly/dist/img/apple-touch-icon-72-precomposed.png">
 <link rel="apple-touch-icon-precomposed" href="UberFireTutorial/patternfly/dist/img/apple-touch-icon-57-precomposed.png">
 <link href="UberFireTutorial/patternfly/dist/css/patternfly.css" rel="stylesheet" media="screen, print">
 <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
 <!--[if lt IE 9]>
 <script src="UberFireTutorial/patternfly/components/html5shiv/dist/html5shiv.min.js"></script>
 <script src="UberFireTutorial/patternfly/components/respond/dest/respond.min.js"></script>
 <![endif]-->
 <!-- IE8 requires jQuery and Bootstrap JS to load in head to prevent rendering bugs -->
 <!-- IE8 requires jQuery v1.x -->
 <script src="UberFireTutorial/patternfly/components/jquery/jquery.min.js"></script>
 <script src="UberFireTutorial/patternfly/components/bootstrap/dist/js/bootstrap.min.js"></script>
 <script src="UberFireTutorial/patternfly/dist/js/patternfly.min.js"></script>
</head>

<body>
 <span id="badge">
  <img src="uf-logo.png" alt="UberFire logo" />
 </span>
 <div class="container">
   <div class="row">
     <div class="col-sm-12">
       <div id="brand">
         UberFire + PatternFly Demo Application
       </div><!--/#brand-->
     </div><!--/.col-*-->
     <div class="col-sm-7 col-md-6 col-lg-5 login">
       <form class="form-horizontal" role="form" action="uf_security_check" method="post">

         <% if (request.getParameter("login_failed") != null) { %>
         <div class="alert alert-danger" role="alert">
           <strong>Login Failed.</strong> Please try again.
         </div>
         <% } %>
         
         <div class="form-group">
           <label for="inputUsername" class="col-sm-2 col-md-2 control-label">Username</label>
           <div class="col-sm-10 col-md-10">
             <input name="uf_username" type="text" class="form-control" id="inputUsername" placeholder="" tabindex="1">
           </div>
         </div>
         <div class="form-group">
           <label for="inputPassword" class="col-sm-2 col-md-2 control-label">Password</label>
           <div class="col-sm-10 col-md-10">
             <input name="uf_password" type="password" class="form-control" id="inputPassword" placeholder="" tabindex="2">
           </div>
         </div>
         
         <% if (request.getParameter("gwt.codesvr") != null) { %>
           <input type="hidden" name="gwt.codesvr" value="<%= org.owasp.encoder.Encode.forHtmlAttribute(request.getParameter("gwt.codesvr")) %>"/>
         <% } %>
         
         <div class="form-group">
           <div class="col-xs-8 col-sm-offset-2 col-sm-6 col-md-offset-2 col-md-6">
         <%--
             <div class="checkbox">
               <label>
                 <input type="checkbox" tabindex="3"> Remember Username
               </label>
             </div>
             <span class="help-block"> Forgot <a href="#" tabindex="5">Username</a> or <a href="#" tabindex="6">Password</a>?</span>
          --%>
           </div>
           <div class="col-xs-4 col-sm-4 col-md-4 submit">
             <button type="submit" class="btn btn-primary btn-lg" tabindex="4">Log In</button>
           </div>
         </div>
       </form>
     </div><!--/.col-*-->
     <div class="col-sm-5 col-md-6 col-lg-7 details">
       <p><strong>Welcome to UberFire with PatternFly!</strong><br>
       <a href="http://uberfireframework.org/">UberFire</a> is a Rich Client Platform for the web.<br>
       <a href="http://www.patternfly.org/">PatternFly</a> Promotes design commonality and improved user experience across enterprise IT products and applications.<br>
       This demo shows the two of them working together.</p>
     </div><!--/.col-*-->
   </div><!--/.row-->
 </div><!--/.container-->
</body>
</html>
