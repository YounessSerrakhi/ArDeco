-steps you should follow to add code effeciently:

//in github
1-fork this github repo
2-go to your forked repo an copy the http link
//in git (LOcal)
3-git clone <urlOfYourForkedRepo>
4-git branch                            ==>this cmd should return (main beta)
5-git checkout -B FEAUTUREbranch beta    ==> this cmd create a new branch from the beta one and switch to it
//start devlopping,ading(git add .) and commiting(git commit -m "kteb ache derti bikhtissar")
6-when you finish go to the forked repo in github and synchronise     //when you are here you shoult annonce that to prevent incomming problemes
// in git
7-git pull origin                             ==> this cmd will update your beta and main branch to the last code
8-git checkout beta                           ==>switch to the beta branch
9-git merge FEATUREbranch                     ==>merge the changes that you made, if the beta branch was changed, for probable they will be some conflicts(machakil eyto ela youness)
10-resolve the conflicts by updating your code
11-git commit -a -m "i change kada kada and i merge into the beta"
//now your beta branch has the recent code with also your change
12-tester si tou va bien
13-git push -f origin       ==>after this cmd the forked repo in your github is uptodate
//now go to your github
14-create a pull request from your beta to my beta
15-cff ana hantkelef, thank you for your engagement :)
