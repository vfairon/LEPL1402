#!/bin/bash

# Define your GitHub username and repository name
github_username="vfairon"
repository_name="LEPL1402"

# Define the directory where your Java files are located
root_directory="/c/Users/vfair/IdeaProjects"



# Clone your repository to your local machine
git clone git@github.com:$github_username/$repositoryName.git

# Navigate to the cloned repository
cd $repositoryName

# Add and commit all .java files in subdirectories
for file in $(find . -name "*.java"); do
    git add "$file"
done
git commit -m "Add .java files"

# Push changes to the GitHub repository
git push origin main

# Check for deleted files and commit/push the deletions
deleted_files=$(git status --porcelain --untracked-files=no | grep "^ D ")
for file in $deleted_files; do
    git rm --cached "$file"
done
if [ ${#deleted_files[@]} -gt 0 ]; then
    git commit -m "Delete files"
    git push origin main
fi
