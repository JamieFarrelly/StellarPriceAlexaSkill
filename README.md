Pay with Fire Alexa
================================

Alexa Skill to check your business Pay with Fire account balances.

Overview
--------------------------
Project that's based off [Amazon's sample projects](https://github.com/amzn/alexa-skills-kit-java) so that you can ask for the likes of
"Alexa, ask Pay with Fire to check my balance" and get back how much money you've got in each of your Fire accounts. See an example of it [here](https://twitter.com/Jamie_Farrelly/status/814134723085791232).

Alexa will then read back the name of each account you have and how much money you have in each account.

To get started
--------------------------

* You'll need a Pay with Fire business account, [you can sign up here](https://paywithfire.com)
* Create an API application in [business.paywithfire.com](https://business.paywithfire.com) and make sure it has permission to get account details
* Clone this repo
* In PayWithFireApi.java change CLIENT_KEY, CLIENT_ID and REFRESH_TOKEN to be whatever your API application details are
* Deploy the skill, I used Lambda since it's the easiest way to get going quickly. [Follow Amazon's docs to deploy the skill](https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/deploying-a-sample-skill-to-aws-lambda)
* Make sure the skill is set to whatever language your device (Echo, Echo Dot etc.) uses, the default is US English but this won't work if yours is set up for UK English

Why you have to deploy the skill yourself
--------------------------
There's no reason why this skill couldn't be available to everyone and be up on the Alexa Skills Store, but there's a bit to think about first...you'd have to store people's CLIENT_KEYs etc. and store them securly since they could be used to make payments in the future, not just read only access. Because of this, I've kept it to work with just one account but feel free to clone the repo and use it yourself.
