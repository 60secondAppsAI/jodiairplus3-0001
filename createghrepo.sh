#!/bin/bash

GITHUB_USERNAME=$1
GITHUB_TOKEN=$2
REPO_NAME=$3
REPO_DESCRIPTION=$4
CODE_DIR=$5
USER_EMAIL=$6
USER_GITHUB_ID=$7

# # Variables (Edit these as needed)
# GITHUB_USERNAME="60secondAppsAI"
# REPO_NAME="braggbay27-0001"
# REPO_DESCRIPTION="This is a braggbay27 repo description"
# CODE_DIR="drcvapps/braggbay27"
# USER_EMAIL="ennis.bragg@fsae.com"

# echo "GitHub Username: $GITHUB_USERNAME"
# echo "GitHub Token: $GITHUB_TOKEN"
# echo "Repository Name: $REPO_NAME"
# echo "Repository Description: $REPO_DESCRIPTION"
# echo "Code Directory: $CODE_DIR"
# echo "User Email: $USER_EMAIL"

# Function to check if required tools are installed
check_requirements() {
  if ! command -v curl &> /dev/null || ! command -v gh &> /dev/null || ! command -v git &> /dev/null
  then
    echo "One or more required tools are missing. Please install curl, gh (GitHub CLI), and git."
    exit 1
  fi
}

# Create a new GitHub repository
create_repo() {
  echo "Creating a new GitHub repository '$REPO_NAME'..."

  curl -u "$GITHUB_USERNAME:$GITHUB_TOKEN" https://api.github.com/user/repos -d "{\"name\":\"$REPO_NAME\", \"description\":\"$REPO_DESCRIPTION\", \"private\":false}"

  if [ $? -ne 0 ]; then
    echo "Failed to create the GitHub repository."
    exit 1
  fi

  echo "Repository '$REPO_NAME' created successfully."
}

# Initialize the Git repository, add files, commit, and push to GitHub
commit_code() {
  echo "Checking in code from '$CODE_DIR'..."

  cd "$CODE_DIR"
  git init
  git remote add origin "https://github.com/$GITHUB_USERNAME/$REPO_NAME"
  git add .
  git commit -m "Initial commit"
  git branch -M main
  git push -u origin main

  if [ $? -ne 0 ]; then
    echo "Failed to push the code to the repository."
    exit 1
  fi

  echo "Code checked in successfully."
}

# Add the user as a collaborator
share_repo() {
  echo "Adding $USER_EMAIL as a collaborator..."

 
 gh api \
    --method PUT \
    -H "Accept: application/vnd.github+json" \
    -H "X-GitHub-Api-Version: 2022-11-28" \
    /repos/$GITHUB_USERNAME/$REPO_NAME/collaborators/$USER_GITHUB_ID \
    -f permission="push"
  

  if [ $? -ne 0 ]; then
    echo "Failed to add the user as a collaborator."
    exit 1
  fi

  echo "User '$USER_EMAIL' has been successfully added to the repository."
}

# Main execution
main() {
check_requirements
  create_repo
  commit_code
  share_repo
}

main
