# Define your GitHub username and repository name
$githubUsername = "vfairon"
$repositoryName = "LEPL1402"

# Define the directory where your Java files are located
$rootDirectory = "C:\Users\vfair\IdeaProjects"

# Navigate to the root directory
cd $rootDirectory

# Initialize a new Git repository in the root directory if not already initialized
if (-not (Test-Path -Path .git -PathType Container)) {
    git init
    git remote add origin "https://github.com/$githubUsername/$repositoryName.git"
    git branch -M main
}

# Add and commit all .java files in subdirectories
Get-ChildItem -Recurse -Filter "*.java" | ForEach-Object {
    git add $_.FullName
}
git commit -m "Add .java files"

# Push changes to the GitHub repository
git push origin main

# Check for deleted files and commit/push the deletions
$deletedFiles = git status --porcelain --untracked-files=no | Where-Object { $_ -match "^ D " }
foreach ($deletedFile in $deletedFiles) {
    $fileToDelete = $deletedFile -replace "^ D ", ""
    git rm --cached $fileToDelete
}
if ($deletedFiles.Count -gt 0) {
    git commit -m "Delete files"
    git push origin main
}

