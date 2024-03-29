/* This file is part of the Scriptic Virtual Machine
 * Copyright (C) 2009 Andre van Delft
 *
 * The Scriptic Virtual Machine is free software: 
 * you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package scriptic.vm;

final class DurationList extends RequestList {
    DurationList(Object owner,String s) {super(owner,s);}

    public String toString() {
        if (!Node.traceOK) return "";
        String result = name+" "+(owner instanceof Node? (""+((Node)owner).instanceNr): owner)+":";
        for (RequestNode prev=null,node=first; node!=null;
                         prev=node,node=node.nextReq) {
            if (prev==null
            ||  prev.priority != node.priority
            ||  prev.duration != node.duration)
            {
                result+="["+node.priority
                      + ","+node.duration
                      + "]";
            }
            result+=node.instanceNr+" ";
        }
	return result;
    }
}
