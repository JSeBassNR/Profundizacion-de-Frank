# Safer recovery: find only textual blobs that match known patterns, then restore.
$matches = Select-String -Path .git\lost-found\other\* -Pattern 'package com.example.sheep|<artifactId>ganadero-service|spring.application.name=ganadero-service' -List -ErrorAction SilentlyContinue
if (-not $matches) { Write-Output 'No matching blobs via Select-String.'; exit 0 }
foreach ($m in $matches) {
  $f = $m.Path
  try { $txt = Get-Content $f -Raw -ErrorAction Stop } catch { Write-Output "SKIP (read error): $f"; continue }
  if ($txt -match '<artifactId>ganadero-service</artifactId>') {
    $out='ganadero-service\pom.xml'
    if (-not (Test-Path $out)) { New-Item -ItemType Directory -Force -Path (Split-Path $out) | Out-Null; Set-Content -Path $out -Value $txt -Force; Write-Output "RECOVERED: $out" } else { Write-Output ("SKIP (exists): {0}" -f $out) }
    continue
  }
  if ($txt -match 'spring.application.name=ganadero-service') {
    $out='ganadero-service\src\main\resources\application.properties'
    if (-not (Test-Path $out)) { New-Item -ItemType Directory -Force -Path (Split-Path $out) | Out-Null; Set-Content -Path $out -Value $txt -Force; Write-Output "RECOVERED: $out" } else { Write-Output ("SKIP (exists): {0}" -f $out) }
    continue
  }
  if ($txt -match 'package\s+(com\.example\.sheep[\w\.]*)') {
    $pkg = $matches[1]
    if ($txt -match 'public\s+(class|interface|enum|record)\s+([A-Za-z0-9_]+)') { $name = $matches[2] }
    elseif ($txt -match '\bclass\s+([A-Za-z0-9_]+)') { $name = $matches[1] }
    else { Write-Output "SKIP (no class name): $f"; continue }
    $sub = $pkg -replace '\.', '\\'
    $out = Join-Path 'ganadero-service\src\main\java' ($sub + '\\' + $name + '.java')
    if (-not (Test-Path $out)) { New-Item -ItemType Directory -Force -Path (Split-Path $out) | Out-Null; Set-Content -Path $out -Value $txt -Force; Write-Output "RECOVERED: $out" } else { Write-Output ("SKIP (exists): {0}" -f $out) }
  }
}
Write-Output 'Done.'
