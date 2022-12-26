# SpigotCommandObfuscator

A plugin to obfuscate command auto complete for players.

## Version

Spigot for Minecraft 1.19

## Configuration

* `commandWhiteList: [true|false]`  
  Defines if the first part of commands should be filtered by whitelist.  
  ( `/this-part` )
* `tabWhiteList: [true|false]`  
  Defines if the tab part of the commands should be filtered by whitelist.  
  ( `/command this-part` )
* `allowedCommands:`  
  List of allowed commands.  
  Every tab command needs to be added to the config separately.
  
### Config Example

```
commandWhiteList: true
tabWhiteList: true
allowedCommands:
  - help
  - help about
```

## Permissions

The whitelist is per default applyed to all players that are not OP.  
Players can bypass the whitelist with the permission `command-obfuscator.bypass`.
