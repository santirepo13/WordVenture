# Fix top navigation "Package" and "Class" across all generated Javadoc HTML files
# Replaces plain text list items with clickable anchors using correct relative paths

$ErrorActionPreference = 'Stop'

$docRoot = (Resolve-Path 'doc').Path

Get-ChildItem -Path $docRoot -Recurse -Filter *.html | ForEach-Object {
  $f = $_.FullName
  $dir = Split-Path -Path $f -Parent

  # Build a Uri for the current directory and for doc root targets
  $from = [System.Uri]::new(($dir + [System.IO.Path]::DirectorySeparatorChar))
  $pkgTarget = [System.Uri]::new((Join-Path $docRoot 'allpackages-index.html'))
  $clsTarget = [System.Uri]::new((Join-Path $docRoot 'allclasses-index.html'))

  # Compute relative hrefs from this file to doc root targets
  $pkgHref = [System.Uri]::UnescapeDataString(($from.MakeRelativeUri($pkgTarget)).ToString())
  $clsHref = [System.Uri]::UnescapeDataString(($from.MakeRelativeUri($clsTarget)).ToString())

  # Load file
  $content = Get-Content -Raw -LiteralPath $f -Encoding UTF8

  # Patterns for plain text nav items (not anchors)
  $pkgPattern = '<li>\s*Package\s*</li>'
  $clsPattern = '<li>\s*Class\s*</li>'

  # If content is not HTML-encoded (normal files), use raw tags too
  if ($content -match '<li>') {
    $new = [regex]::Replace($content, $pkgPattern, "<li><a href='$pkgHref'>Package</a></li>")
    $new = [regex]::Replace($new, $clsPattern, "<li><a href='$clsHref'>Class</a></li>")
  } else {
    $pkgPatternRaw = '<li>\s*Package\s*</li>'
    $clsPatternRaw = '<li>\s*Class\s*</li>'
    $new = [regex]::Replace($content, $pkgPatternRaw, "<li><a href='$pkgHref'>Package</a></li>")
    $new = [regex]::Replace($new, $clsPatternRaw, "<li><a href='$clsHref'>Class</a></li>")
  }

  if ($new -ne $content) {
    Set-Content -LiteralPath $f -Value $new -Encoding UTF8
    Write-Output "Patched: $f"
  }
}