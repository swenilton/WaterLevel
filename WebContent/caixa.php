<!DOCTYPE html>
<!-- saved from url=(0039)http://fiddle.jshell.net/4r2uu0h6/show/ -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <title>Vertical Progress Bar - jsFiddle demo</title>
  
  <script type="text/javascript" src="./Vertical Progress Bar - jsFiddle demo_files/jquery-1.9.1.js"></script>
  
  <link rel="stylesheet" type="text/css" href="./Vertical Progress Bar - jsFiddle demo_files/normalize.css">
  
  
  <link rel="stylesheet" type="text/css" href="./Vertical Progress Bar - jsFiddle demo_files/result-light.css">
  
  <style type="text/css">
.skill {
    position: relative;
}
.outer {
    width: 720px;
    height: 370px;
    overflow: hidden;
	top: 127px;
    position: absolute;
    
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}

.inner, .inner div {
    width: 100%;
    overflow: hidden;
    left: -2px;
    position: absolute;
}

.inner {
    border: 2px solid #85CAED;
    border-top-width: 0;
    background-color: #85CAED;
    
    bottom: 0;
    height: 0%;
}

.inner div {
    border: 2px solid #38A8E0;
    border-bottom-width: 0;
    background-color: #38A8E0;

    text-align: center;
    color: #fff;
    font-family: Verdana;
    font-size: 7pt;
    
    top: 0;
    width: 100%;
    height: 10px;
}

.caixa {
    position: absolute;
}

  </style>
  


<script type="text/javascript">//<![CDATA[ 
$(function(){
    $('.skill div').load('.inner', function(){
        var skillBar = $(this).siblings().find('.inner');
        var skillVal = skillBar.attr("data-progress");
        $(skillBar).animate({
            height: skillVal
        }, 1000);
    });
});//]]> 
</script>
</head>
<body>
    <form name="form" method="post" action="">
        <input type="text" name="valor"/>
        <input type="submit" name="btn" value="ok"/>
    </form>
<?php
    $i = '0%';
    /*
    $arquivo = fopen("COM5", "w+") or die("deu merda");
    if ($arquivo) {
            while (!feof($arquivo)) {
                $linha = fgetss($arquivo);
                echo $linha;
                sleep(1);
            }
    }
    */
    if(isset($_POST["btn"])) {
        $valor = $_POST["valor"];
        if($valor > 100 || $valor < 0) echo "Valor inválido";
        else $i = $valor.'%';
    }
    
    echo "  <div class='skill'>
                <div class='outer'>
                    <div class='inner' data-progress='$i'>
                        <div>$i</div>
                    </div>        
                </div>
                <div class='caixa'>
                    <img src='img/Caixa de água 3.png'/>            
                </div>
            </div>";
?>
</body>
</html>