<html lang="en" class=" is-copy-enabled">
<head>
<meta charset='utf-8'>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta name="viewport" content="width=1020">

<link rel="canonical" href="https://github.com/motech-implementations/ebodac/master/README.md" data-pjax-transient>
</head>


<body class="logged-out env-production windows vis-public page-blob">

<article class="markdown-body entry-content" itemprop="text">
<h2>Synopsis</h2>

<b>EBODAC</b> is an open source product created for a Clinical Trials. It has been deployed for an <b>EBOLA Trial</b> to run in Sierra Leone.</br>
It is an implementation of the MOTECH Platform (to have more details visit <a href="http://motechproject.org" >MOTECH Project Website</a>). 

</article>
<article class="markdown-body entry-content" itemprop="text">
<h2>Motivation</h2>
We wanted to support all the Clinical Trials in the world with an open source product developed for <a href="http://www.grameenfoundation.org/" >Grameen Foundations</a> by <a href="www.soldevelo.com">SolDevelo</a>.
</article>
<article class="markdown-body entry-content" itemprop="text">
<h2>Installation (Linux / Mac / Windows)  </h2>
<h3>Motech Platform Installation</h3>


To intall <b>Ebodac</b> , you need first to install Motech Platform. If you'd like to install and run the latest MOTECH binaries, go <a href="http://docs.motechproject.org/en/latest/get_started/installing.html">here</a>.

If you'd prefer to build MOTECH yourself, try <a href="http://docs.motechproject.org/en/latest/development/dev_setup/dev_install.html">these instructions</a> instead.

After instaling MOTECH Github package, move to the 0.27.x branch:

<pre>
$ git fetch origin
$ git checkout origin 0.27.x
</pre>

The <a href="http://docs.motechproject.org/en/latest/development/dev_setup/dev_install.html">main steps</a> to install Motech via Tomcat:
<ul>
<li>
After installing the motech and start tomcat you should watch an screen with the process of the installation in the http://localhost:8080/{name of the war}/bootstrap </li>
<li> Select the default values for the activemq and mysql installation. </li>
<li>Then you should watch an screen where you will have the motech finishing installing where you will get the question to select the Repository to add the user/password </li>
<li>Finally you will get the screen to create the motech user admin to use motech.</li>
</ul>


Now we need to install the modules and EBODAC.

<h3>Modules Platform Installation</h3> 


For the installation with the modules we need to go to here:

<pre>
$ git clone https://github.com/motech/modules.git
$ git fetch origin
$ git checkout origin 0.27.x
</pre>

After creation of folder modules and adding all the files there , you have to:

<pre>
$ cd modules
</pre>

Access to the SMS module  and install it:

<pre>
$ cd sms
$mvn clean install -Dmaven.test.skip=true
</pre>

Then you install the ivr module:

<pre>
$ cd ../ivr
$ mvn clean install -Dmaven.test.skip=true
</pre>

Then the message campaign module:
<pre>
$ cd ../message-campaign
$ mvn clean install -Dmaven.test.skip=true
</pre>

Then the CSD (Openhie module):

<pre>
$ cd ../csd
$ mvn clean install -Dmaven.test.skip=true
</pre>

<h3>Ebodac Platform Installation</h3>

You have to get out from the modules folder and clone the ebodac implementation:

<pre>
$ git clone https://github.com/motech-implementations/ebodac.git
</pre>

This will create for you the ebodac folder with the files inside , then you have to go inside and compile it:

<pre>
$ cd ebodac
$ mvn clean install -Dmaven.test.skip=true
</pre>

Then you should have a success message.
</article>

<article class="markdown-body entry-content" itemprop="text">
<h2>Tests</h2>


We have developed Unit Tests and Integration test which could be found :
<ul>
<li><a href="https://github.com/motech-implementations/ebodac/tree/master/ebodac/src/test/java/org/motechproject/ebodac">Ebodac Unit Tests & Integration Tests</a></li>
<li><a href="https://github.com/motech-implementations/ebodac/tree/master/booking-app/src/test" >Booking App Unit Tests and Integration Tests</a></li>
<li><a href="https://github.com/motech-implementations/ebodac/tree/master/ebodac/src/test/java/org/motechproject/ebodac/uitest">UI Tests</a></li>
</ul>
</article>

<article class="markdown-body entry-content" itemprop="text">
<h2>Contributing</h2>

We welcome contributions from the open source community. For instructions on how to get started as a MOTECH contributor, please check out the <a href="http://docs.motechproject.org/en/latest/contribute/index.html">Contribute</a> section of our documentation.

</article>
<article class="markdown-body entry-content" itemprop="text">
<h2>Disclaimer Text Required By GF Legal Team</h2>


Third party technology may be necessary for use of MOTECH 2.0. This agreement does not modify or abridge any rights or obligations you have in open source technology under applicable open source licenses.</br>

Open source technology programs that are separate from MOTECH are provided as a courtesy to you and are licensed solely under the relevant open source license. </br>
Any distribution by you of code licensed under an open source license, whether alone or with MOTECH, must be under the applicable open source license.


</article>

</body>
</html>
