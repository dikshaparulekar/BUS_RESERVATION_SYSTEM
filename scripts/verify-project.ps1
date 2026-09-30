param(
    [string]$Command = "verify"
)

Write-Host "=========================================" -ForegroundColor Cyan
Write-Host " BUS SEAT RESERVATION SYSTEM - VERIFIER  " -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan

function Check-Tool {
    param([string]$Name, [string]$CheckCmd)
    try {
        $result = Invoke-Expression $CheckCmd 2>&1
        Write-Host "[OK] $Name is installed and working." -ForegroundColor Green
        return $true
    } catch {
        Write-Host "[WARN] $Name is NOT available: $_" -ForegroundColor Yellow
        return $false
    }
}

Write-Host "`n--- Checking Environment Tools ---" -ForegroundColor Header
Check-Tool "Java" "java -version"
Check-Tool "Maven" "D:\apache-maven-3.9.16\bin\mvn.cmd -version"
Check-Tool "Git" "git --version"
Check-Tool "Docker CLI" "docker --version"

Write-Host "`n--- Testing Maven Application Build ---" -ForegroundColor Header
Set-Location D:\Downloads\BUS_RESERVATION_SYSTEM
$buildResult = & 'D:\apache-maven-3.9.16\bin\mvn.cmd' -s D:\maven-settings.xml test-compile 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "[OK] Application source code compiles successfully." -ForegroundColor Green
} else {
    Write-Host "[FAIL] Maven compilation failed." -ForegroundColor Red
}

Write-Host "`n--- Verification Complete ---" -ForegroundColor Cyan
